import React from "react";
import { Button } from "@chakra-ui/react";
import { useClient } from "../../hooks/client";

export const RegiterButton: React.VFC<{}> = () => {
  const client = useClient();

  return (
    <Button
      fontSize={"sm"}
      fontWeight={600}
      color={"white"}
      bg={"blue.400"}
      _hover={{
        bg: "blue.300",
      }}
      onClick={() => {
        client.getOAuthUrl().then((data) => {
          window.open(data.url);
        });
      }}
    >
      Twitterにログインして登録
    </Button>
  );
};

export default RegiterButton;
