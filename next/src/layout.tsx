import React from "react";
import { Header } from "./components/global/Header";
import { Footer } from "./components/global/Footer";

export const Layout: React.FC<{}> = ({ children }) => (
  <div>
    <div>
      <Header />
      {children}
    </div>
    <Footer />
  </div>
);
