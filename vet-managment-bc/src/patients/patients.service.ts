import { Inject, Injectable, NotFoundException } from '@nestjs/common';
import { CreatePatientDto } from './dto/create-patient.dto';
import { UpdatePatientDto } from './dto/update-patient.dto';
import { InjectRepository } from '@nestjs/typeorm';
import { Patient } from './entities/patient.entity';
import { Repository } from 'typeorm';
import { CatchErrors } from 'src/common/decorators/catch-errors.decorator';

import { PatientQueryDto } from './dto/patient-query.dto';
import { Tutor } from 'src/tutors/entities/tutor.entity';
import { LoggerService } from 'src/common/services';
import { ExceptionHandlerService } from 'src/common/services/exception-handler.service';

@Injectable()
@CatchErrors()
export class PatientsService {
  constructor(
    @InjectRepository(Patient) private patientsRepository: Repository<Patient>,
    @InjectRepository(Tutor) private tutorsRepository: Repository<Tutor>,
    @Inject(LoggerService)
    public readonly loggerService: LoggerService,
    @Inject(ExceptionHandlerService)
    public readonly exceptionHandlerService: ExceptionHandlerService,
  ) { }

  async create(createPatientDto: CreatePatientDto) {

    const tutor: Tutor = await this.tutorsRepository.findOneBy({ id: createPatientDto.tutorId});

    if (!tutor) throw new NotFoundException('Tutor was not found');

    const newPatient: Patient = this.patientsRepository.create({ ...createPatientDto, tutor });

    return await this.patientsRepository.save(newPatient);

  }

  async findWithQueryParams(patientQuery: PatientQueryDto) {

    const { tutorId, specie, tutorIdentity, limit = 10, page = 1 } = patientQuery;
    console.log(tutorId, specie, tutorIdentity, limit, page);
    

    const query = this.patientsRepository.createQueryBuilder('patient')
    .leftJoinAndSelect('patient.tutor', 'tutor');

    if (tutorId) {
      const tutor = await this.tutorsRepository.findOne({ where: { id: patientQuery.tutorId } });
      if (!tutor) throw new NotFoundException('Tutor was not found');
      query.andWhere(`patient.tutor = :tutor`, { tutor: tutor.id });
    }

    if (specie) {
      query.andWhere('patient.specie = :specie', { specie: patientQuery.specie });
      if(!specie) throw new NotFoundException('Specie was not found');
    }

    if (tutorIdentity) {
      query.andWhere('tutor.identificationNumber = :identity', { identity: patientQuery.tutorIdentity });
      if(!tutorIdentity) throw new NotFoundException('Tutor identity was not found');
    }

    query.skip((page - 1) * limit).take(limit);

    const [patients, totalPatients] = await query.getManyAndCount();

     return {
        data: patients,
        totalPatients,
        totalPages: Math.ceil(totalPatients / limit),
        currentPage: page,
    };
  }

  async findOne(id: number) {
    const patient: Patient = await this.patientsRepository.findOne({ where: { id }, relations: ['tutor'] });

    if (!patient) throw new NotFoundException('Patient was not found');

    return patient;
  }

  async update(id: number, updatePatientDto: UpdatePatientDto) {
    const result = await this.patientsRepository.update(id, updatePatientDto);

    if (!result.affected) throw new NotFoundException('Patient not found');

    return await this.patientsRepository.findOne({ where: { id } });
  }

  async remove(id: number) {

    const patient: Patient = await this.findOne(id);
    await this.patientsRepository.softDelete(id);

    return patient;
  }
}
