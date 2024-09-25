import { PATIENTS_API_ENDPOINTS, TEndpointKeys } from './patients.endpoints';
import { axiosInstanceManagmentAppoitments } from '../../config/axios.config';
import { ICreatePatient } from './interfaces/createPatient.interface';
import { IPatchPatient } from './interfaces/patch-patient.interface';

export interface ITutor {
    id: number;
    name: string;
    identificationNumber: number;
    userId: string;
  }

export interface IPatients {
    id: number;
    name: string;
    specie: string;
    breed: string;
    gender: string;
    dob: string;
    weight: number;
    alimentation: string;
    sterilized: boolean | null;
    color: string | null;
    tutor: ITutor; 
} 

const endpoints = (method: TEndpointKeys, id?: string) => {
    return PATIENTS_API_ENDPOINTS(id)[method];
}

export class PatientsService {

    static async getAll(query?: string): Promise<IPatients[]> {
        const endpoint = endpoints('GET_ALL')
        const { data } = await axiosInstanceManagmentAppoitments.get(`${endpoint}?${query && query}`);
        return data.data;
    }

    static async getOne(id: string): Promise<IPatients> {
        const endpoint = endpoints('GET_ONE', id)
        const { data } = await axiosInstanceManagmentAppoitments.get(endpoint);
        return data.data;
    }

    static async create(body: ICreatePatient) {
        const endpoint = endpoints('CREATE')
        const { data } = await axiosInstanceManagmentAppoitments.post(endpoint, body);
        return data;
    }

    static async update(body: IPatchPatient, id: string) {
        const endpoint = endpoints('UPDATE', id)
        const { data } = await axiosInstanceManagmentAppoitments.patch(endpoint, body);
        return data;
    }
    
}