import styled from '@emotion/styled';
import { colors } from '@toss/tds-colors';

// 상단 통계 카드 영역
export const StatsGrid = styled.div`
  display: flex;
  gap: 12px;
  margin-bottom: 24px;
`;

export const StatCard = styled.div<{ bgColor?: string }>`
  flex: 1;
  display: flex;
  flex-direction: column;
  padding: 16px;
  border-radius: 16px;
  background-color: ${({ bgColor }) => bgColor || colors.grey100};
  gap: 8px;
`;
