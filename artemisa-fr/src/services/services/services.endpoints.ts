export const SERVICES_API_ENDPOINTS = (id?: string) => {
    return {
        GET_ALL: 'services',
        GET_BY_ID: `services${id}`
    }
}
export type TEndpointKeys = 'GET_ALL' | 'GET_BY_ID';