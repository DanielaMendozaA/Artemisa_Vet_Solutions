import axios, { AxiosInstance } from 'axios';

export const baseApiUrlUsers = 'http://localhost:3001/api/';

export const baseApiUrlManagmetAppoitments = 'http://localhost:3002/api/';

const axiosInstanceUsers = axios.create({
    baseURL: baseApiUrlUsers,
    timeout: 10000,
    headers: {
        'Content-Type': 'application/json',
    },
});

axiosInstanceUsers.interceptors.request.use(
    (config) => {
        const token = sessionStorage.getItem('token');
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

const axiosInstanceManagmentAppoitments: AxiosInstance = axios.create({
    baseURL: baseApiUrlManagmetAppoitments,
    timeout: 10000,
    headers: {
        'Content-Type': 'application/json'
    },
    // withCredentials: true
});

axiosInstanceManagmentAppoitments.interceptors.request.use(
    (config) => {
        const token = sessionStorage.getItem('token');
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

const axiosInstanceFormData: AxiosInstance = axios.create({
    // baseURL: import.meta.env.VITE_APP_API_URL || 'https://service.coworking.riwicloud.com/api/',
    baseURL: baseApiUrlManagmetAppoitments,
    timeout: 10000,
    headers: {
        'Content-Type': 'multipart/form-data'
    },
});

axiosInstanceFormData.interceptors.request.use(
    (config) => {
        const token = sessionStorage.getItem('token');
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);



export {
    axiosInstanceUsers, axiosInstanceManagmentAppoitments, axiosInstanceFormData
};