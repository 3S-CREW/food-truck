import styled from "@emotion/styled";
import { colors } from "@toss/tds-colors";

// 하단 공지 바
export const NoticeBar = styled.div`
  display: flex;
  align-items: center;
  padding: 16px;
  background-color: ${colors.blue50};
  border-radius: 16px;
  gap: 8px;
  margin-top: auto;
  margin-bottom: 20px;
`;
