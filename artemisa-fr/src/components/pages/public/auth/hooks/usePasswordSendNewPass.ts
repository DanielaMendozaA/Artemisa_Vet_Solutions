import { AuthService } from "@/services/auth.service";

interface IPasswordChange {
    password: string;
    token: string;
  }
  

  export const usePasswordNewPass = async ({ password, token }: IPasswordChange): Promise<any> => {
    try {
        const response = await AuthService.sendNewPassword({
            token,       
            password
        });

        console.log("Respuesta del servidor:", response, "nueva contraseña enviada", password);
        return response.statusCode;
    } catch (error: any) {
        console.error("Error en la petición desde hook", error);
        return error.message;
    }
};
