import styled from '@emotion/styled';
import { colors } from '@toss/tds-colors';

// 전체 컨테이너
export const Container = styled.div`
  position: relative; 
  flex-direction: column;
  width: 100%;
  height: 100vh;
  background-color: ${colors.grey100};
  overflow: hidden;
`;

export const TopFixedBar = styled.div`
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 0px 24px;
  background-color: ${colors.white};
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
  flex-shrink: 0;
  z-index: 10;
`;

export const SearchFieldWrapper = styled.div`
  flex: 1;
  display: flex;
  align-items: center;
`;

// 지도 플레이스홀더 (나중에 네이버 지도 컴포넌트로 대체될 영역)
export const MapPlaceholder = styled.div`
  width: 100%;
  height: 100%;
  background-color: #e5e5e5;
  position: relative;
  background-image: linear-gradient(#ccc 1px, transparent 1px),
    linear-gradient(90deg, #ccc 1px, transparent 1px);
  background-size: 50px 50px;
`;

// 더미 마커 스타일
export const MockMarker = styled.div`
  position: absolute;
  transform: translate(-50%, -100%);
  display: flex;
  flex-direction: column;
  align-items: center;
  cursor: pointer;

  .marker-icon {
    width: 40px;
    height: 40px;
    background-color: ${colors.blue500};
    border-radius: 50%;
    border: 3px solid white;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 20px;
    box-shadow: 0 4px 6px rgba(0,0,0,0.2);
    position: relative;
    z-index: 2;
  }

  .marker-label {
    margin-top: 4px;
    background-color: white;
    padding: 4px 8px;
    border-radius: 8px;
    font-size: 12px;
    font-weight: bold;
    color: ${colors.grey800};
    box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  }
`;

// 하단 내 위치 버튼
export const BottomFloatingButton = styled.div`
  position: absolute;
  bottom: 34px;
  right: 20px;
  z-index: 10;
`;