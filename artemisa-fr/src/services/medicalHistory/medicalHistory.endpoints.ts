export const HISTORY_API_ENDPOINTS = (id?: string) => {
    return {
        GET_ALL_OR_FILTER: `medical-history-record`,
        GET_BY_ID: `medical-history-record/${id}`,
        GET_FILE: `medical-history-record/file/${id}`,
        CREATE: `medical-history-record`
    }
}
export type TEndpointKeys = 'GET_ALL_OR_FILTER' | 'GET_BY_ID' | 'GET_FILE' | 'CREATE';