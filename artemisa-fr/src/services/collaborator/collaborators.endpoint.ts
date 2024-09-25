export const COLLABORATORS_API_ENDPOINTS = (id?: string) => {
    return {
        GET_ALL: `collaborators`,
        GET_BY_ID: `collaborators${id}`
    }
}
export type TEndpointKeys = 'GET_ALL' | 'GET_BY_ID';