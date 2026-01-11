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

// 신고 내역 카드
export const ReportCard = styled.div<{ isHighlighted?: boolean }>`
  display: flex;
  flex-direction: column;
  padding: 20px;
  border-radius: 20px;
  border: 2px solid
    ${({ isHighlighted }) => (isHighlighted ? colors.red100 : colors.grey300)};
  background-color: ${({ isHighlighted }) =>
    isHighlighted ? colors.red50 : colors.grey50};
`;

// 카드 내부 회색 박스 (신고 내용)
export const ContentBox = styled.div`
  background-color: white;
  padding: 16px;
  border-radius: 12px;
  margin: 12px 0;
  display: flex;
  flex-direction: column;
  gap: 4px;
`;

// 카드 하단 정보 (신고자, 날짜)
export const InfoRow = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 16px;
`;

// 버튼 그룹
export const ButtonGroup = styled.div`
  display: flex;
  gap: 8px;
  width: 100%;
`;

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
