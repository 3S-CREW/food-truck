import styled from '@emotion/styled';
import { colors } from '@toss/tds-colors';

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