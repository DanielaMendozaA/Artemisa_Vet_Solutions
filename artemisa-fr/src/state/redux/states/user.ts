// Importing required libraries

import { IUser } from "@/models/interfaces";
import { createSlice, PayloadAction } from "@reduxjs/toolkit";

// Define the initial state based on the provided response structure
export const emptyUserState: IUser = {
  id: "",
  email: "",
  role: "",
  name: "",
};

// Create the user slice
export const userSlice = createSlice({
  name: "user",
  initialState: emptyUserState,
  reducers: {
    setUser: (state, action: PayloadAction<IUser>) => {
      state.id = action.payload.id;
      state.email = action.payload.email;
      state.name = action.payload.name;
      // state.emailVerified = action.payload.emailVerified;
      // state.emailVerifiedAt = action.payload.emailVerifiedAt;
      // state.password_attempts = action.payload.password_attempts;
      // state.name = action.payload.name;
      // state.documentType = action.payload.documentType;
      // state.documentNumber = action.payload.documentNumber;
      // state.cellphone = action.payload.cellphone;
      // state.isActive = action.payload.isActive;
      state.role = action.payload.role;
    },
  },
});

// Export the actions from the user slice
export const { setUser } = userSlice.actions;

// Export the reducer to be used in the store
export default userSlice.reducer;
