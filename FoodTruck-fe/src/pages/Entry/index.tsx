import { Button, TextButton } from '@toss/tds-mobile';
import * as S from '@/pages/Entry/style';
import { useNavigate } from 'react-router-dom';
import PageHeader from '@/components/layout/EntryPageHeader';

const EntryPage = () => {
  const navigate = useNavigate();

  const handleAdminLoginClick = () => {
    navigate('/admin/login');
  };

  return (
    <S.Container>
      {/* 상단 ~ 중앙 콘텐츠 영역 */}
      <PageHeader
        title={`우리 동네\n푸드트럭`}
        subtitle='토스로 로그인 할게요'
      />

      {/* 하단 버튼 영역 */}
      <S.ButtonWrapper>
        <Button color='primary' variant='fill' style={{ width: '100%' }}>
          토스로 시작하기
        </Button>
        <TextButton size='medium' type='button' onClick={handleAdminLoginClick}>
          관리자 로그인
        </TextButton>
      </S.ButtonWrapper>
    </S.Container>
  );
};

export default EntryPage;
