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

// 하단 전체 영역 래퍼
export const FormWrapper = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
`;

// 입력창 그룹
export const InputGroup = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  margin-bottom: 12px;
`;

// 버튼 그룹 (가로 배치)
export const ButtonGroup = styled.div`
  display: flex;
  flex-direction: row;
  width: 100%;
  gap: 10px;
  padding-inline: 20px;
`;