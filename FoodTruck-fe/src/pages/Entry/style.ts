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

// 하단 버튼 영역
export const ButtonWrapper = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  gap: 16px;
  align-items: center;
`;