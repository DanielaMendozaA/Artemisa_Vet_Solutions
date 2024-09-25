import { Gender, Species } from "@/models/enums/pet.enums";

export interface IPatchPatient {
    name?: string;
    specie?: string;
    breed?: string;
    gender?: string;
    dob?: string;
    weight?: number;
    alimentation?: string;
    color?: string;

}