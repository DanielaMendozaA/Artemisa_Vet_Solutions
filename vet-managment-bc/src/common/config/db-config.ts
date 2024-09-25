import { Injectable } from '@nestjs/common';
import { TypeOrmOptionsFactory, TypeOrmModuleOptions } from '@nestjs/typeorm';
import { ConfigService } from '@nestjs/config';
import { Appointment } from 'src/appointments/entities/appointment.entity';
import { Collaborator } from 'src/collaborators/entities/collaborator.entity';
import { Service } from 'src/services/entities/service.entity';
import { Shift } from 'src/shifts/entities/shift.entity';
import { Patient } from 'src/patients/entities/patient.entity';
import { Tutor } from 'src/tutors/entities/tutor.entity';
import { TestResult } from 'src/tests-results/entities/test-result.entity';
import { MedicalHistoryRecord } from 'src/medical-history-record/entities/medical-history-record.entity';
import { Payment } from 'src/payments/entities/payment.entity';


@Injectable()
export class DatabaseConfigService implements TypeOrmOptionsFactory {
  constructor(private configService: ConfigService) {}

  createTypeOrmOptions(): TypeOrmModuleOptions {
    return {
      type: 'postgres',
      host: this.configService.get<string>('DB_HOST'),
      port: this.configService.get<number>('DB_PORT'),
      username: this.configService.get<string>('DB_USERNAME'),
      password: this.configService.get<string>('DB_PASSWORD'),
      database: this.configService.get<string>('DB_NAME'),
      entities: [Patient, Tutor, Appointment, Collaborator, Service, Collaborator, Shift, TestResult, Payment, MedicalHistoryRecord],
      synchronize: true, // Solo para desarrollo, no usar en producción
    };
  }
}
