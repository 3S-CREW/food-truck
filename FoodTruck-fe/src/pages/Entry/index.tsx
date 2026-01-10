import { Asset, Button, Text, TextButton } from '@toss/tds-mobile';
import { colors } from '@toss/tds-colors';
import * as S from '@/pages/Entry/style';

const EntryPage = () => {
  return (
    <S.Container>
      {/* 상단 ~ 중앙 콘텐츠 영역 */}
      <S.ContentWrapper>
        <Asset.Frame
          shape={{ width: 100, height: 100 }}
          content={
            // 트럭 이모지
            <Asset.ContentImage
              src="https://static.toss.im/3d-emojis/u1F69A-apng.png"
              alt="트럭 이모지"
            />
          }
        />

        <S.TextGroup>
          {/* 타이틀 영역 */}
          <div style={{ textAlign: 'center' }}>
            <Text typography="t1" fontWeight="bold" display="block">
              우리 동네
            </Text>
            <Text typography="t1" fontWeight="bold" display="block">
              푸드트럭
            </Text>
          </div>

          {/* 부가 설명 */}
          <div style={{ marginTop: '12px' }}>
            <Text color={colors.grey500} typography="t5">
              토스로 로그인 할게요
            </Text>
          </div>
        </S.TextGroup>
      </S.ContentWrapper>

      {/* 하단 버튼 영역 */}
      <S.ButtonWrapper>
        <Button color="primary" variant="fill" style={{ width: '100%' }}>
          토스로 시작하기
        </Button>
        <TextButton size="medium" type="button">
          관리자 로그인
        </TextButton>
      </S.ButtonWrapper>
    </S.Container>
  );
};

export default EntryPage;
