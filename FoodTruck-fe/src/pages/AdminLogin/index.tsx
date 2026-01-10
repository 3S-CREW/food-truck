import PageHeader from '@/components/layout/EntryPageHeader';
import { TextField, Button } from '@toss/tds-mobile';
import * as S from './style';

const AdminLoginPage = () => {
  return (
    <S.Container>
      {/* 상단 콘텐츠 영역 */}
      <PageHeader
        title={`우리 동네\n푸드트럭`}
        subtitle='토스로 로그인 할게요'
      />

      {/* 하단 폼 영역 (입력창 + 버튼) */}
      <S.FormWrapper>
        <S.InputGroup>
          <TextField
            variant='box'
            labelOption='sustain'
            placeholder='관리자 아이디 입력'
            paddingTop={0}
            paddingBottom={10}
          />
          <TextField.Password
            variant='box'
            labelOption='sustain'
            placeholder='관리자 비밀번호 입력'
            paddingTop={5}
            paddingBottom={10}
          />
        </S.InputGroup>

        <S.ButtonGroup>
          <Button color='primary' variant='fill' style={{ flex: 1 }}>
            로그인
          </Button>
          <Button color='dark' variant='weak' style={{ flex: 1 }}>
            돌아가기
          </Button>
        </S.ButtonGroup>
      </S.FormWrapper>
    </S.Container>
  );
};

export default AdminLoginPage;
