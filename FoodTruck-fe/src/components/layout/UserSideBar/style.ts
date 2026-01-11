import styled from '@emotion/styled';
import { colors } from '@toss/tds-colors';

export const Overlay = styled.div<{ isOpen: boolean }>`
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 20;
  opacity: ${({ isOpen }) => (isOpen ? 1 : 0)};
  visibility: ${({ isOpen }) => (isOpen ? 'visible' : 'hidden')};
  transition: opacity 0.3s ease;
`;

export const Drawer = styled.div<{ isOpen: boolean }>`
  position: fixed;
  top: 0;
  left: 0;
  width: 280px;
  height: 100vh;
  background-color: white;
  z-index: 21;
  transform: ${({ isOpen }) => (isOpen ? 'translateX(0)' : 'translateX(-100%)')};
  transition: transform 0.3s ease;
  padding: 24px;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
`;

export const CloseButton = styled.div`
  position: absolute;
  top: 16px;
  right: 16px;
  cursor: pointer;
`;

export const ProfileSection = styled.div`
  display: flex;
  align-items: center;
  gap: 12px;
  margin-top: 40px;
  margin-bottom: 40px;
`;

export const MenuSection = styled.div`
  display: flex;
  flex-direction: column;
  gap: 24px;
  flex: 1;
`;

export const MenuRow = styled.div`
  cursor: pointer;
`;

export const LogoutSection = styled.div`
  margin-top: auto;
  padding-bottom: 20px;
`;