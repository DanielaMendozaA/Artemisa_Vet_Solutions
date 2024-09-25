import { IPatients } from "@/services/patients/patients.service";
import IServiceResponse from "@/services/services/interfaces/servicesResponse.interface";

interface ITestResultResponse {
    id: number;
    fileName: string;
    fileMimetype: string;
    filePath: string;
    date: string;
    service: IServiceResponse;
    patient: IPatients;
}

export default ITestResultResponse;