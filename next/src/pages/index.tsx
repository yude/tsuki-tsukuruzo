import React, { useEffect, useState } from "react";
import { useClient } from "../hooks/client";
import RegisterButton from "../components/common/RegisterButton";
import UnregisterButton from "../components/common/UnregisterButton";
import {
  Box,
  Flex,
  Image,
  Text,
  Heading,
  useColorModeValue,
} from "@chakra-ui/react";
import { Status } from "../types/struct";

const IndexPage: React.VFC<{}> = () => {
  const client = useClient();

  const [status, setStatus] = useState<Status | null>(null);

  useEffect(() => {
    client.getStatus().then((data) => {
      setStatus(data);
    });
  }, []);

  return (
    <Box>
      <Flex
        bg={useColorModeValue("white", "gray.800")}
        color={useColorModeValue("gray.600", "white")}
        minH={"60px"}
        py={{ base: 2 }}
        px={{ base: 4 }}
        align={"center"}
        direction="column"
      >
        <Flex flex={{ base: 1 }} justify={{ base: "center" }}>
          <Heading as="h2" size="xl">
            月作るぞ #tsuki_tsukuruzo
          </Heading>
        </Flex>

        <Image src="tsuki.jpg" alt="tsuki" mt="3" />

        <Flex direction="row" alignItems="center" mt="3">
          <Box mr="10">
            <RegisterButton />
          </Box>
          <Box>
            <UnregisterButton />
          </Box>
        </Flex>

        <Text mt="2">
          月曜日の午前0時になると 月作るぞ をするTwitterアプリです。
          <br />
          毎週 月曜日 午前0時になると「月作るぞ
          #tsuki_tsukuruzo」をツイートします。
          <br />
          現在{status === null ? 0 : status.usersSize}
          人のユーザーが利用しています
        </Text>
      </Flex>
    </Box>
  );
};

export default IndexPage;
