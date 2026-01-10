import * as S from '@/pages/Terms/style';
import { colors } from '@toss/tds-colors';
import { Button, Text, TableRow } from '@toss/tds-mobile';
import { TERMS_OF_SERVICE, PRIVACY_POLICY_TABLE } from '@/constants/terms';

const TermsPage = () => {
  return (
    <S.Container>
      <S.ContentWrapper>
        {/* 타이틀 영역 */}
        <S.TitleSection>
          <Text typography='t2' fontWeight='bold'>
            {TERMS_OF_SERVICE.TITLE}
          </Text>
          <S.Subtitle>
            <Text typography='t6' color={colors.grey500}>
              {TERMS_OF_SERVICE.SUBTITLE}
            </Text>
          </S.Subtitle>
        </S.TitleSection>

        {/* 스크롤 영역 */}
        <S.ScrollSection>
          <Text typography='t5' color={colors.grey700}>
            {TERMS_OF_SERVICE.COLLECTIONS_OF_PERSONAL_INFORMATION_TEXT}
          </Text>
          {/* 첫 번째 동의 섹션 */}
          <S.SectionGroup>
            <Text typography='t4' fontWeight='bold'>
              {TERMS_OF_SERVICE.FIRST_AGREE_TITLE}
            </Text>
            <div>
              <TableRow
                align='left'
                left={PRIVACY_POLICY_TABLE.FIRST_LEFT}
                right={PRIVACY_POLICY_TABLE.FIRST_RIGHT}
                leftRatio={PRIVACY_POLICY_TABLE.LEFT_RATIO}
              />
              <TableRow
                align='left'
                left={PRIVACY_POLICY_TABLE.SECOND_LEFT}
                leftFontWeight='bold'
                right={PRIVACY_POLICY_TABLE.SECOND_RIGHT}
                leftRatio={PRIVACY_POLICY_TABLE.LEFT_RATIO}
              />
              <TableRow
                align='left'
                left={PRIVACY_POLICY_TABLE.THIRD_LEFT}
                leftFontWeight='bold'
                right={PRIVACY_POLICY_TABLE.THIRD_RIGHT}
                leftRatio={PRIVACY_POLICY_TABLE.LEFT_RATIO}
              />
              <TableRow
                align='left'
                left={PRIVACY_POLICY_TABLE.FOURTH_LEFT}
                leftFontWeight='bold'
                right={PRIVACY_POLICY_TABLE.FOURTH_RIGHT}
                leftRatio={PRIVACY_POLICY_TABLE.LEFT_RATIO}
              />
              <TableRow
                align='left'
                left={PRIVACY_POLICY_TABLE.FIFTH_LEFT}
                leftFontWeight='bold'
                right={PRIVACY_POLICY_TABLE.FIFTH_RIGHT}
                leftRatio={PRIVACY_POLICY_TABLE.LEFT_RATIO}
              />
            </div>
          </S.SectionGroup>
          {/* 두 번째 동의 섹션 */}
          <S.SectionGroup>
            <Text typography='t4' fontWeight='bold'>
              {TERMS_OF_SERVICE.SECOND_AGREE_TITLE}
            </Text>
            <Text typography='t5' color={colors.grey700} style={{ lineHeight: '1.5' }}>
              {TERMS_OF_SERVICE.PRESERVATION_OF_PERSONAL_INFORMATION_TEXT}
            </Text>
          </S.SectionGroup>
        </S.ScrollSection>
      </S.ContentWrapper>

      {/* 하단 버튼 */}
      <Button color='primary' variant='fill' style={{ width: '100%' }}>
        닫기
      </Button>
    </S.Container>
  );
};

export default TermsPage;
