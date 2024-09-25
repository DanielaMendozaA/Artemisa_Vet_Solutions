import { axiosInstanceFormData, axiosInstanceManagmentAppoitments } from "@/config/axios.config";
import { TEndpointKeys, TEST_RESULTS_API_ENDPOINTS } from "./testResults.endpoints";
import ITestResultResponse from "./interfaces/testResultResponse.interface";
import ICreateTestResult from "./interfaces/createTestResult.interface";

const endpoints = (method: TEndpointKeys, id?: string) => {
    return TEST_RESULTS_API_ENDPOINTS(id)[method];
}

export class TestResultsService {

    static async getAllOrFilter(query: string): Promise<ITestResultResponse> {
        const endpoint = endpoints('GET_ALL_OR_FILTER')
        const { data } = await axiosInstanceManagmentAppoitments.get(`${endpoint}?${query && query}`);
        return data.data;
    }

    static async getFile(id: string): Promise<Blob>  {
        const endpoint = endpoints('GET_FILE', id);
        const { data } = await axiosInstanceManagmentAppoitments.get<Blob>(endpoint, {
            responseType: 'blob',
        });
        return data;
    }

    static async uploadFile(body: ICreateTestResult) {
        const endpoint = endpoints('UPLOAD_FILE');
        const { data } = await axiosInstanceFormData.post(endpoint, body);
        return data;
    }
}