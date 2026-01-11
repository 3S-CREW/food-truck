import * as S from '@/pages/Entry/style';
import { Button } from '@toss/tds-mobile';
import { useNavigate } from 'react-router-dom';

const TempEntryPage = () => {
  const navigate = useNavigate();

  const handleAdminDashBoardClick = () => {
    navigate('/admin/dashboard');
  };

  const handleUserHomeClick = () => {
    navigate('/user/home');
  };

  return (
    <S.Container>
      {/* 임시 버튼 영역 */}
      <S.ButtonWrapper>
        <Button
          color='primary'
          variant='fill'
          style={{ width: '100%' }}
          onClick={handleAdminDashBoardClick}
        >
          관리자 대시보드
        </Button>
        <Button
          color='primary'
          variant='fill'
          style={{ width: '100%' }}
          onClick={handleUserHomeClick}
        >
          사용자 홈 화면
        </Button>
      </S.ButtonWrapper>
    </S.Container>
  );
};

export default TempEntryPage;
