import * as S from '@/components/layout/NoticeBar/style';
import { Paragraph } from '@toss/tds-mobile';
import { colors } from '@toss/tds-colors';

const NoticeBar = () => {
  return (
    <S.NoticeBar>
      <Paragraph typography="t5">
        <Paragraph.Icon name="icon-bulb" />
        <Paragraph.Text
          typography="t6"
          color={colors.blue700}
          fontWeight="medium"
          style={{ paddingLeft: '8px' }}
        >
          숨김 처리 시 사용자에게 즉시 비노출됩니다.
        </Paragraph.Text>
      </Paragraph>
    </S.NoticeBar>
  );
};

export default NoticeBar;