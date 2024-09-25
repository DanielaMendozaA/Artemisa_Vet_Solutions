import { AppointmentState } from "@/models/enums/appointmentState.enum";

export interface ICreateAppointment {
    date: string;
    time: string;
    serviceId:  number;
    patientId: number;
    collaboratorId: number;
}

export interface IUpdateAppointent {
    state: AppointmentState;
}