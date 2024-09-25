export const APPOINTMENTS_API_ENDPOINTS = (id?: string) => {
    return {
        GET_ALL_OR_FILTER: `appointments`,
        GET_BY_ID: `appointments/${id}`,
        GET_AVAILABILITY: `appointments/available`,
        CREATE: `appointments`,
        PATCH: `appointments/${id}`
    }
}
export type TEndpointKeys = 'GET_ALL_OR_FILTER' | 'GET_BY_ID' | 'GET_AVAILABILITY' | 'CREATE' | 'PATCH';