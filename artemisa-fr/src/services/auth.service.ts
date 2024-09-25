import { axiosInstanceManagmentAppoitments, axiosInstanceUsers } from "../config/axios.config";
import { ILoginReq, ILoginResponse, IRecoverPasswordReq, IRegisterReq, IRegisterResponse } from "../models/interfaces/auth.interface";
import { TEndpoitKeys, USERS_API_ENDPOINTS } from "./auth/auth-endpoints";

const getEndpoint = (method: TEndpoitKeys) : string => {

    return USERS_API_ENDPOINTS()[method];

}

export class AuthService{
    static login = async (req: ILoginReq) : Promise <ILoginResponse> => {
        const endpoint = getEndpoint('LOGIN');
            try {
                const loginResponse = await axiosInstanceUsers.post(endpoint, req);
                return loginResponse.data;
            } catch (error: any) {
                throw new Error(error.message);
            }
        }

    static register = async (req: IRegisterReq ) : Promise <IRegisterResponse> => {
        const endpoint = getEndpoint('REGISTER');
        try {
            const registerResponse = await axiosInstanceManagmentAppoitments.post(endpoint, req);
            return registerResponse.data;
        } catch (error: any) {
            throw new Error(error.message);
        }

    }

    static recoverPassword = async (req : IRecoverPasswordReq) : Promise <any> => {
        const endpoint = getEndpoint('RECOVER_PASSWORD');
        try {
            const recoverPasswordResponse = await axiosInstanceUsers.post(endpoint, req);
            return recoverPasswordResponse.data;
        } catch (error: any) {
            throw new Error(error.message);
        }

    }

    static sendNewPassword = async ({ token, password }: { token: string, password: string }): Promise<any> => {
        try {
            const url = `${getEndpoint('SEND_NEW_PASSWORD')}?token=${token}`;
            console.log("nueva contraseña enviada desde auth", password);
            
            const sendNewPasswordResponse = await axiosInstanceUsers.patch(url, { password });
            return sendNewPasswordResponse.data;
        } catch (error: any) {
            throw new Error(error.message);
        }
    };
    

    

        static logout = (): void => {
          sessionStorage.removeItem("token");
        };
}


    //Todo Reactquery -> Toolkit 