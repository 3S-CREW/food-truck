import styled from '@emotion/styled';

// 전체 화면을 감싸는 컨테이너
export const Container = styled.div`
  display: flex;
  flex-direction: column;
  height: 100vh;
  padding: 0 24px;
  padding-bottom: 34px;
  box-sizing: border-box;
  background-color: white;
`;

// 상단 콘텐츠 영역
export const ContentWrapper = styled.div`
  flex: 1;
  display: flex;
  flex-direction: column;
  min-height: 0;
  margin-bottom: 24px;
`;

export const TitleSection = styled.div`
  margin-top: 24px;
  margin-bottom: 32px;
  flex-shrink: 0;
`;

export const Subtitle = styled.div`
  margin-top: 8px;
`;

export const ScrollSection = styled.div`
  flex: 1;
  overflow-y: auto;
  -ms-overflow-style: none;
  scrollbar-width: none;
  &::-webkit-scrollbar {
    display: none;
  }
  display: flex;
  flex-direction: column;
  gap: 32px;
`;

// 개별 약관 섹션 (제목 + 내용 묶음)
export const SectionGroup = styled.div`
  display: flex;
  flex-direction: column;
  gap: 12px; /* 섹션 제목과 테이블/내용 사이의 간격 */
`;
