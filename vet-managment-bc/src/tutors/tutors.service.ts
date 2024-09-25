import { Inject, Injectable, NotFoundException } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';

import { CreateTutorDto } from './dto/create-tutor.dto';
import { UpdateTutorDto } from './dto/update-tutor.dto';
import { Tutor } from './entities/tutor.entity';
import { CatchErrors } from 'src/common/decorators/catch-errors.decorator';
import { userPath } from 'src/common/docs/users-service-path';
import { IHttpAdapter } from 'src/common/interfaces';
import { LoggerService } from 'src/common/services';
import { ExceptionHandlerService } from 'src/common/services/exception-handler.service';
import { TutorQueryDto } from './dto/tutors-query.dto';

@Injectable()
@CatchErrors()
export class TutorsService {
  constructor(
    @InjectRepository(Tutor) private tutorsRepository: Repository<Tutor>,
    @Inject('IHttpAdapter') private readonly httpAdapter: IHttpAdapter,
    @Inject(LoggerService)
    public readonly loggerService: LoggerService,
    @Inject(ExceptionHandlerService)
    public readonly exceptionHandlerService: ExceptionHandlerService, 
  ) { }

  
  async create(createTutorDto: CreateTutorDto) {
    const newUser = await this.httpAdapter.post<{ data: string }>(userPath + '/auth/register', {
      email: createTutorDto.email,
      name: createTutorDto.name,
      password: createTutorDto.password,
      cellphone: createTutorDto.cellphone
    });

    const userId = newUser.data;

    const newTutor = this.tutorsRepository.create({ identificationNumber: createTutorDto.identificationNumber, userId, name: createTutorDto.name });
    return await this.tutorsRepository.save(newTutor);
  }

  async findAllOrFilter(tutorQuery: TutorQueryDto) {
    const query = this.tutorsRepository.createQueryBuilder('tutor')
    
    if(tutorQuery.idNumber) {
      query.where('tutor.identificationNumber = :identificationNumber', {identificationNumber: tutorQuery.idNumber});
      return await query.getOne();
    }

    return await query.getMany();
  }

  async findByUserId(id: string) {
    const tutor = await this.tutorsRepository.findOne({ where: {userId: id}});

    if(!tutor) throw new NotFoundException('Tutor by user ID not found');

    return tutor;
  }

  async findOne(id: number) {
    const tutor = await this.tutorsRepository.findOne({ where: { id }, relations: ['patients'] });

    if (!tutor) throw new NotFoundException('Tutor not found');

    return tutor;
  }

  async update(id: number, updateTutorDto: UpdateTutorDto) {

    //Falta update en users
    const result = await this.tutorsRepository.update(id, updateTutorDto);

    if (!result.affected) throw new NotFoundException('Tutor was not found');

    return await this.findOne(id);
  }

  async remove(id: number) {
    const tutor = await this.findOne(id);

    await this.tutorsRepository.softDelete(id);

    return tutor;
  }
}
