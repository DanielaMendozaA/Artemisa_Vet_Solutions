import { axiosInstanceManagmentAppoitments } from "@/config/axios.config";
import { TEndpointKeys, TUTORS_API_ENDPOINTS } from "./tutors.endpoints";
import ITutorsResponse from "./interfaces/interfaces";

const endpoints = (method: TEndpointKeys, id?: string) => {
    return TUTORS_API_ENDPOINTS(id)[method];
}

export class TutorsService {

    static async getByUserId(id: string): Promise<ITutorsResponse> {
        const endpoint = endpoints('GET_BY_USER_ID', id)
        const { data } = await axiosInstanceManagmentAppoitments.get(`${endpoint}`);
        return data.data;
    }

    static async getAllOrFilter(query?: string): Promise<ITutorsResponse> {
        const endpoint = endpoints('GET_ALL_OR_FILTER');
        const { data } = await axiosInstanceManagmentAppoitments.get(`${endpoint}?${query && query}`);
        return data.data;
    }
}