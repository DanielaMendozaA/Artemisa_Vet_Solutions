export const USERS_API_ENDPOINTS = () => {
    const resource = 'auth/';

    return{
        LOGIN: `${resource}login`,
        REGISTER: 'tutors',
        RECOVER_PASSWORD: 'users/change-password',
        SEND_NEW_PASSWORD: 'tokens/change-password'
    }
};

export type TEndpoitKeys = keyof ReturnType<typeof USERS_API_ENDPOINTS>;