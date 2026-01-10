import styled from '@emotion/styled';

// 전체 화면을 감싸는 컨테이너
export const Container = styled.div`
  display: flex;
  flex-direction: column;
  height: 100vh;
  padding: 0 12px;
  padding-bottom: 34px;
  box-sizing: border-box; 
  background-color: white;
`;

// 하단 버튼 영역
export const ButtonWrapper = styled.div`
  width: 100%;
  display: flex;
  flex-direction: column;
  padding-inline: 20px;
  gap: 16px;
  align-items: center;
`;