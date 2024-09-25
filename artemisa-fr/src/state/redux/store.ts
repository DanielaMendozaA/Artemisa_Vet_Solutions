import { configureStore } from "@reduxjs/toolkit";
import { userSlice } from "./states";
import { IUser } from "@/models/interfaces";

export interface IAppStore {
  user: IUser;
}

const store = configureStore<IAppStore>({
  reducer: {
    user: userSlice.reducer,
  },
});

export default store;
