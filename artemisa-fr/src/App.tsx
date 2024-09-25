import { PrivateRoutes, PublicRoutes } from "./models/routes/routes.model";
import { materialTheme } from "./state/context/theme";
import { Route } from "react-router-dom";
import { RoutesPlusNotFound } from "./components/utilities/routes-with-notFound.component";
import Home from "./components/pages/public/home/home.component";
import Auth from "./components/pages/public/auth/auth.component";
import RecoverPasswordEmail from "./components/pages/public/auth/recover-password/recoverPasswordEmail.component";
import RecoverPasswordNewPassword from "./components/pages/public/auth/recover-password/recoverPasswordNewPass.component";
import Guard from "./components/guards/user-and-token-validation.guard";
import UserLayout from "./components/layout/user.layout";
import Appointments from './components/pages/private/appointments/appointments.component';
import AdminAppointments from "./components/pages/private/adminAppointments/adminAppointments.component";
import { CssBaseline, ThemeProvider } from "@mui/material";
import PatientsComponent from "./components/pages/private/patients/patients.component";
import MedicalHistoryForm from "./components/pages/private/adminAppointments/components/medicalHistoryForm.component";


function App() {
  //Logica
  return (
    //Todo lo que retorna el componente App se renderiza en el componente root
    // Todo: - snackbar providers - and guards
    <ThemeProvider theme={materialTheme}>
      <CssBaseline enableColorScheme />
      <RoutesPlusNotFound>
        <Route element={<Guard isForAuth/>}>
          <Route path={PublicRoutes.LOGIN} element={<Auth initialView="login" />} />
          <Route path={PublicRoutes.REGISTER} element={<Auth initialView="register" />} />
          
        </Route>
        <Route element={<Guard isForAuth />}>
          {/* <Route path={PublicRoutes.RECOVER_PASSWORD_NEW_PASS} element={<RecoverPasswordNewPassword />}></Route> */}
        </Route>
        <Route path={PublicRoutes.RECOVER_PASSWORD} element={<RecoverPasswordEmail />}></Route>
        <Route path={PublicRoutes.RECOVER_PASSWORD_NEW_PASS} element={<RecoverPasswordNewPassword />}></Route>

        <Route element={<UserLayout />}>
          <Route path="/" element={<Home />} />
          <Route element={<Guard privateValidation/>}>
            <Route path="/appointments" element={<Appointments />} />
          </Route>
          <Route element={<Guard privateValidation isAdminValidation/>}>
            <Route path="/admin/appointments" element={<AdminAppointments />} />
            <Route path="/history" element={<MedicalHistoryForm />} />
            <Route path={PrivateRoutes.ALL_PATIENTS} element={<PatientsComponent/>}></Route>
          </Route>  
        </Route>
      </RoutesPlusNotFound>
    </ThemeProvider>
  );
}

export default App;
