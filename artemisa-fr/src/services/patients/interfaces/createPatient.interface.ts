import { Gender, Species } from "@/models/enums/pet.enums";

export interface ICreatePatient {
    name: string;
    specie: string;
    breed?: string;
    gender: string;
    dob?: string;
    weight?: number;
    alimentation?: string;
    color?: string;
    tutorId: number;

}