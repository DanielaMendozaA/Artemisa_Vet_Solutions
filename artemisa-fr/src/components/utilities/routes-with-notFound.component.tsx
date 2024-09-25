import { Route, Routes } from "react-router-dom";
import NotFound from "../pages/public/not-found/not-found.component";
// import { NotFound } from "../pages/public/404";

interface IProps {
  children: JSX.Element | JSX.Element[];
}

export const RoutesPlusNotFound = ({ children }: IProps) => {
  return (
    <Routes>
      {children}
      <Route path="*" element={<NotFound/>}/>
    </Routes>
  );
};