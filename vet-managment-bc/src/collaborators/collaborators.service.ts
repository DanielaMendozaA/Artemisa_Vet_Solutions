import { Inject, Injectable, NotFoundException } from '@nestjs/common';
import { CreateCollaboratorDto } from './dto/create-collaborator.dto';
import { UpdateCollaboratorDto } from './dto/update-collaborator.dto';
import { InjectRepository } from '@nestjs/typeorm';
import { Collaborator } from './entities/collaborator.entity';
import { Repository } from 'typeorm';
import { CatchErrors } from 'src/common/decorators/catch-errors.decorator';
import { ServicesService } from 'src/services/services.service';
import { ShiftsService } from 'src/shifts/shifts.service';
import { userPath } from 'src/common/docs/users-service-path';
import { CollaboratorQueryDto } from './dto/collaborator-query.dto';
import { IHttpAdapter } from 'src/common/interfaces';
import { LoggerService } from 'src/common/services';
import { ExceptionHandlerService } from 'src/common/services/exception-handler.service';

@Injectable()
@CatchErrors()
export class CollaboratorsService {

  constructor(
    private readonly serviceService: ServicesService,
    private readonly shiftService: ShiftsService,
    @InjectRepository(Collaborator) private collaboratorRepository: Repository<Collaborator>,
    @Inject('IHttpAdapter') private readonly httpAdapter: IHttpAdapter,
    @Inject(LoggerService)
    public readonly loggerService: LoggerService,
    @Inject(ExceptionHandlerService)
    public readonly exceptionHandlerService: ExceptionHandlerService,
  ) {} 
  async create(createCollaboratorDto: CreateCollaboratorDto) {
    const newUser = await this.httpAdapter.post<{ data: string }>( userPath + '/auth/register', {
      email: createCollaboratorDto.email,
      name: createCollaboratorDto.name,
      password: createCollaboratorDto.password,
      cellphone: createCollaboratorDto.cellphone,
      role: 'collaborator'
    });
    const userId = newUser.data;
    let servicesExists = true;
    const services = [];
    for (const serviceId of createCollaboratorDto.servicesId) {
      const service = await this.serviceService.findOne(serviceId);
      if (!service) {
        servicesExists = false;
        break; 
      }
      services.push(service);
    }
    if (!servicesExists) {
      throw new NotFoundException('One or more services not found');
    }
    const shift = await this.shiftService.findOneByName(createCollaboratorDto.shiftName);
    if (!shift) {
      throw new NotFoundException('Shift not found');
    }
    const newCollaborator = this.collaboratorRepository.create({
      shift : shift,
      userId,
      services: services,
      name: createCollaboratorDto.name
    });  

    return this.collaboratorRepository.save(newCollaborator);
  }

 
  async findWithQueryParams(collaboratorQuery: CollaboratorQueryDto) {
    const query = this.collaboratorRepository.createQueryBuilder('collaborator')
      .leftJoinAndSelect('collaborator.shift', 'shift')
      .leftJoinAndSelect('collaborator.services', 'services');

    if (collaboratorQuery.shift) {
      const shift = await this.shiftService.findOneByName(collaboratorQuery.shift);
      query.andWhere('collaborator.shift = :shift', { shift: shift.id });
    }
    if (collaboratorQuery.serviceId) {
      const service = await this.serviceService.findOne(collaboratorQuery.serviceId);
      query.andWhere('services.id = :serviceId', { serviceId: collaboratorQuery.serviceId });
    }
    const collaborators = await query.getMany();
    if (!collaborators || collaborators.length === 0) {
      throw new NotFoundException('Collaborators not found');
    }
    return collaborators;
  }


  async findOne(id: number) {
    const collaborator = this.collaboratorRepository.findOne({where:{id}, relations: ['shift', 'services']});
    if (!collaborator) {
      throw new NotFoundException(`Collaborator #${id} not found`);
    }
    return collaborator;
  }

  async findAllByShift(shiftName: string) {
    const shift = await this.shiftService.findOneByName(shiftName);
    if (!shift) {
      throw new NotFoundException('Shift not found');
    }
    const collaborators = await this.collaboratorRepository.find({where:{shift}, relations: ['shift', 'services']});
    if (!collaborators || collaborators.length === 0) {
      throw new NotFoundException('Collaborators not found');
    }
    return collaborators;
  }

  async restoreCollaborator(id: number): Promise<void> {
    const result = await this.collaboratorRepository.restore(id);
    if (!result.affected) {
      throw new NotFoundException(`Collaborator with ID ${id} not found`);
    }
    return;
  }

  async findAllByService(serviceId: number) {
    const service = await this.serviceService.findOne(serviceId);
    if (!service) {
      throw new NotFoundException('Service not found');
    }
    const collaborators = await this.collaboratorRepository.createQueryBuilder('collaborator')
      .leftJoinAndSelect('collaborator.services', 'service')
      .where('service.id = :serviceId', {serviceId})
      .getMany();
    if (!collaborators || collaborators.length === 0) {
      throw new NotFoundException('Collaborators not found');
    }
    return collaborators;
  }

  async update(id: number, updateCollaboratorDto: UpdateCollaboratorDto) {
    const collaborator = await this.collaboratorRepository.findOne({where:{id}});
    if (!collaborator) {
      throw new NotFoundException(`Collaborator #${id} not found`);
    }
    let servicesExists = true;
    const services = [];
    for (const serviceId of updateCollaboratorDto.servicesId) {
      const service = await this.serviceService.findOne(serviceId);
      if (!service) {
        servicesExists = false;
        break; 
      }
      services.push(service);
    }
    if (!servicesExists) {
      throw new NotFoundException('One or more services not found');
    }
    const shift = await this.shiftService.findOneByName(updateCollaboratorDto.shiftName);
    if (!shift) {
      throw new NotFoundException('Shift not found');
    }
    collaborator.shift = shift;
    collaborator.services = services;
    return this.collaboratorRepository.save(collaborator);
  }

  async remove(id: number) {
    const collaborator = await this.collaboratorRepository.findOne({where:{id}});
    if (!collaborator) {
      throw new NotFoundException(`Collaborator #${id} not found`);
    }
    return this.collaboratorRepository.softDelete(collaborator);
  }
}
