import * as React from "react";
import Router from "next/router";
import { AppProps } from "next/app";
import { ChakraProvider, extendTheme } from "@chakra-ui/react";
import { Layout } from "../layout";
import colors from "../../lib/colors";
import "../styles/main.css";

const theme = extendTheme({
  colors,
});

const App: React.VFC<AppProps> = ({ Component, pageProps }: AppProps) => {
  return (
    <ChakraProvider theme={theme}>
      <Layout>
        <Component {...pageProps} />
      </Layout>
    </ChakraProvider>
  );
};

export default App;
