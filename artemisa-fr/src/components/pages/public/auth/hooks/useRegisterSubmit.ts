import { AuthService } from "@/services/auth.service";

type IProps = {
    email: string;
    name: string;
    password: string;
    cellphone: string;
    identificationNumber: number;
}

export const useRegisterSubmit = async ({ email, name, password, cellphone, identificationNumber }: IProps): Promise<any> => {
    try {
        const response = await AuthService.register({
            email,
            name,
            password,
            cellphone,
            identificationNumber
        });
        console.log("Respuesta del servidor:", response);
        return response.statusCode;
    } catch (error: any) {
        console.error("Error en la petición:", error.message);
        return error.message;
    }
}