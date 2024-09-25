import { AuthService } from "@/services/auth.service";

interface IProps {
    email: string;
}
export const usePasswordRecovery = async ({ email }: IProps): Promise<any> => {
    try {
        const response = await AuthService.recoverPassword({ email });
        console.log("Respuesta del servidor:", response);
        return response.statusCode;
    } catch (error: any) {
        console.error("Error en la petición desde hook", error.message);
        return error.message;
    }
}