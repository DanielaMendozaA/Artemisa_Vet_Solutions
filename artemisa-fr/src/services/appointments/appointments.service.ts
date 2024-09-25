import { APPOINTMENTS_API_ENDPOINTS, TEndpointKeys } from './appointments.endpoints';
import { ICreateAppointment, IUpdateAppointent } from './interfaces/appointment.interfaces';
import { axiosInstanceManagmentAppoitments } from '../../config/axios.config';
import IAppointmentResponse from './interfaces/appointmentResponse.interface';

const endpoints = (method: TEndpointKeys, id?: string) => {
    return APPOINTMENTS_API_ENDPOINTS(id)[method];
}

export class AppointmentsService {

    static async getAllOrFilter(query?: string) {
        const endpoint = endpoints('GET_ALL_OR_FILTER');
        const {data} = await axiosInstanceManagmentAppoitments.get(`${endpoint}?${query && query}`);
        return data.data;
    }

    static async getAvailability(query: string) {
        const endpoint = endpoints('GET_AVAILABILITY')
        const {data} = await axiosInstanceManagmentAppoitments.get(`${endpoint}?${query}`)
        return data.data;
    }

    static async getById(id: string): Promise<IAppointmentResponse> {
        const endpoint = endpoints('GET_BY_ID', id);
        const {data} = await axiosInstanceManagmentAppoitments.get(endpoint);
        return data.data;
    }

    static async create(body: ICreateAppointment) {
        const endpoint = endpoints('CREATE');
        const {data} = await axiosInstanceManagmentAppoitments.post(endpoint, body)
        return data;
    }

    static async patch(id: string, body: IUpdateAppointent) {
        const endpoint = endpoints('PATCH', id);
        const {data} = await axiosInstanceManagmentAppoitments.patch(endpoint, body)
        return data;
    }
}