export const TUTORS_API_ENDPOINTS = (id?: string) => {
    return {
        GET_ALL_OR_FILTER: `tutors`,
        GET_BY_ID: `tutors/${id}`,
        GET_BY_USER_ID: `tutors/user/${id}`
    }
}
export type TEndpointKeys = 'GET_ALL_OR_FILTER' | 'GET_BY_ID' | 'GET_BY_USER_ID';