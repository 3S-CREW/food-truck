import styled from '@emotion/styled';

// 중앙 콘텐츠 영역 (아이콘, 타이틀)
export const ContentWrapper = styled.div`
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
`;

// 텍스트 간격을 위한 래퍼
export const TextGroup = styled.div`
  margin-top: 24px;
  margin-bottom: 48px;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 8px;
`;
