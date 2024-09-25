interface IContent {
    previousIllnesses: string,
    consultationReason: string,
    respiratoryRate: number,
    heartRate: number,
    pulse: string,
    CRT: number;
    temperature: number;
    limphaticNodes: string;
    mucosa: string;
    temperament: string;
    findings: string;
    tests: string;
}

interface IPatientState {
    weight: number;
    alimentation: string;
    sterilized: boolean;
}

export default interface ICreateHistory {
    content: IContent;
    patientState: IPatientState;
    patientId: number;
    appointmentId: number;
}