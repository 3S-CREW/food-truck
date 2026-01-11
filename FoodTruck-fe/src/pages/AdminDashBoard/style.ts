import styled from '@emotion/styled';
import { colors } from '@toss/tds-colors';

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  height: 100vh;
  padding: 0 20px;
  padding-bottom: 34px;
  background-color: white;
  overflow-y: auto;
`;

// 헤더 (제목 + 로그아웃)
export const Header = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px 0;
  margin-bottom: 12px;
`;

// 탭 아래 리스트 영역
export const ListContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 16px;
  margin-top: 20px;
  margin-bottom: 24px;
`;
