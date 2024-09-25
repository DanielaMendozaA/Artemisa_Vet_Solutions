import IAppointmentResponse from "@/services/appointments/interfaces/appointmentResponse.interface";
import { IPatients } from "@/services/patients/patients.service";

interface IContent {
    CRT: number;
    pulse: string;
    tests: string;
    mucosa: string;
    findings: string;
    heartRate: number;
    temperament: string;
    temperature: number;
    limphaticNodes: string;
    respiratoryRate: number;
    previousIllnesses: string;
    consultationReason: string;
  }
  
  interface IPatientState {
    weight: number;
    alimentation: string;
  }

  interface IHistoryResponse {
    id: string;
    content: IContent;
    patientState: IPatientState;
    patient: IPatients;
    appointment: IAppointmentResponse; 
  }

  export default IHistoryResponse;