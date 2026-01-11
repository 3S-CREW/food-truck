import styled from '@emotion/styled';
import { colors } from '@toss/tds-colors';

export const TruckCardContainer = styled.div<{ isHidden: boolean }>`
  display: flex;
  flex-direction: column;
  padding: 20px;
  border-radius: 24px;
  background-color: white;
  border: 1px solid ${({ isHidden }) => 
    isHidden ? colors.red100 : colors.grey200};
  .top-section {
    display: flex;
    justify-content: space-between;
    align-items: flex-start;
  }

  .info-group {
    display: flex;
    align-items: center;
    gap: 12px;
  }

  .text-info {
    display: flex;
    flex-direction: column;
    gap: 2px;
  }
`;

export const IconWrapper = styled.div`
  width: 48px;
  height: 48px;
  border-radius: 50%;
  background-color: ${colors.blue50};
  display: flex;
  align-items: center;
  justify-content: center;
`;

export const BadgeGroup = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  gap: 6px;
`;