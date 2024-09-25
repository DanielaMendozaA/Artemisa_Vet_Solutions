import { COLLABORATORS_API_ENDPOINTS, TEndpointKeys } from './collaborators.endpoint';
import { axiosInstanceManagmentAppoitments } from '../../config/axios.config';
import ICollaboratorResponse from './interfaces/collaboratorResponse.interface';



const endpoints = (method: TEndpointKeys, id?: string) => {
    return COLLABORATORS_API_ENDPOINTS(id)[method];
}

export class CollaboratorsService {

    static async getAll(query?: string): Promise<ICollaboratorResponse[]> {
        const endpoint = endpoints('GET_ALL')
        const { data } = await axiosInstanceManagmentAppoitments.get(`${endpoint}?${query && query}`);
        return data.data;
    }
}