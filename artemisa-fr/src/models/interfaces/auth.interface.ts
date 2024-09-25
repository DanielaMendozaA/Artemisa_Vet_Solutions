export interface ILoginReq {
    email: string;
    password: string;

}

export interface ILoginResponse {
  data:{
    email: string;
    id: string;
    role: string;
    token: string;
    name: string;
  }  
  statusCode: number;
}

export interface IRegisterReq {
  email: string;
  name: string;
  password: string;
  cellphone: string;
  identificationNumber: number;
}

export interface IRegisterResponse {
  data:{
    email: string;
    name: string;
    password: string;
    cellphone: string;
    identificationNumber: number;
  }
  statusCode: number;
}

export interface IRecoverPasswordReq {
  email: string;
}