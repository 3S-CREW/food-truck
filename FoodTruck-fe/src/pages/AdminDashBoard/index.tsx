import DashboardStats from '@/components/layout/DashBoardStats';
import DashboardTabs from '@/components/layout/DashBoardTabs';
import * as S from '@/pages/AdminDashBoard/style';
import { colors } from '@toss/tds-colors';
import {
  Badge,
  Button,
  ListRow,
  Paragraph,
  Text,
  TextButton,
} from '@toss/tds-mobile';
import { useState, type CSSProperties } from 'react';

// mock 데이터 (추후에 API로 받아올때 제거)
const STATS_DATA = [
  { label: '총 신고', count: 3, color: colors.blue700, bgColor: colors.blue50 },
  {
    label: '미처리',
    count: 2,
    color: colors.orange400,
    bgColor: colors.orange50,
  },
  { label: '처리', count: 1, color: colors.green400, bgColor: colors.green50 },
];

const AdminDashBoardPage = () => {
  const [selectedTab, setSelectedTab] = useState(0);

  return (
    <S.Container>
      {/* 헤더 */}
      <S.Header>
        <Text typography='t2' fontWeight='bold'>
          관리자 대시보드
        </Text>
        <TextButton size='medium' color={colors.red500}>
          로그아웃
        </TextButton>
      </S.Header>

      {/* 상단 통계 카드 */}
      <DashboardStats stats={STATS_DATA} />

      {/* 탭 */}
      <DashboardTabs selected={selectedTab} onChange={setSelectedTab} />
      
      {/* 리스트 영역 */}
      <S.ListContainer>
        <S.ReportCard isHighlighted={true}>
          <ListRow
            left={<ListRow.AssetIcon name='icon-chat-bubble-grayline-mono' />}
            contents={
              <ListRow.Texts
                type='2RowTypeA'
                top='한식 트럭'
                bottom='리뷰 신고'
              />
            }
            right={
              <Badge size='small' color='red' variant='weak'>
                노출 중
              </Badge>
            }
            style={{ padding: 0 }}
          />

          <S.ContentBox>
            <Text typography='t5' fontWeight='medium'>
              음식이 ** 맛없어요
            </Text>
            <Text typography='t6' color={colors.grey600}>
              신고 사유: 욕설 포함
            </Text>
          </S.ContentBox>

          <S.InfoRow>
            <Text typography='t6' color={colors.grey600}>
              신고자: 닉네임A
            </Text>
            <Text typography='t7' color={colors.grey500}>
              2024-12-23
            </Text>
          </S.InfoRow>

          <S.ButtonGroup>
            <Button
              size='medium'
              style={{ flex: 1, backgroundColor: colors.orange400 }}
            >
              <Text typography='t6' color={colors.whiteOpacity900}>
                숨김
              </Text>
            </Button>
            <Button
              size='medium'
              style={
                {
                  width: '80px',
                  '--button-background-color': colors.red500,
                } as CSSProperties
              }
            >
              <Text typography='t6' color={colors.whiteOpacity900}>
                삭제
              </Text>
            </Button>
          </S.ButtonGroup>
        </S.ReportCard>

        <S.ReportCard isHighlighted={true}>
          <ListRow
            left={<ListRow.AssetIcon name='icon-pin-location-mono' />}
            contents={
              <ListRow.Texts
                type='2RowTypeA'
                top='타코야끼 천국'
                bottom='푸드트럭 신고'
              />
            }
            right={
              <Badge size='small' color='red' variant='weak'>
                노출 중
              </Badge>
            }
            style={{ padding: 0 }}
          />

          <S.ContentBox>
            <Text typography='t5' fontWeight='medium'>
              위생 상태가 불량함
            </Text>
            <Text typography='t6' color={colors.grey600}>
              신고 사유: 음식 품질 불량
            </Text>
          </S.ContentBox>

          <S.InfoRow>
            <Text typography='t6' color={colors.grey600}>
              신고자: 닉네임B
            </Text>
            <Text typography='t7' color={colors.grey500}>
              2024-12-22
            </Text>
          </S.InfoRow>

          <S.ButtonGroup>
            <Button
              size='medium'
              style={{ flex: 1, backgroundColor: colors.orange400 }}
            >
              <Text typography='t6' color={colors.whiteOpacity900}>
                숨김
              </Text>
            </Button>
            <Button
              size='medium'
              style={
                {
                  width: '80px',
                  '--button-background-color': colors.red500,
                } as CSSProperties
              }
            >
              <Text typography='t6' color={colors.whiteOpacity900}>
                삭제
              </Text>
            </Button>
          </S.ButtonGroup>
        </S.ReportCard>

        <S.ReportCard
          isHighlighted={false}
          style={{ border: `1px solid ${colors.grey200}` }}
        >
          <ListRow
            left={<ListRow.AssetIcon name='icon-chat-bubble-grayline-mono' />}
            contents={
              <ListRow.Texts
                type='2RowTypeA'
                top='핫도그 마을'
                bottom='푸드트럭 신고'
              />
            }
            right={
              <Badge size='small' variant='weak' color='elephant'>
                숨김
              </Badge>
            }
            style={{ padding: 0 }}
          />

          <S.ContentBox>
            <Text typography='t5' fontWeight='medium'>
              먹고 배탈났어요
            </Text>
            <Text typography='t6' color={colors.grey600}>
              신고 사유: 음식 품질 불량
            </Text>
          </S.ContentBox>

          <S.InfoRow>
            <Text typography='t6' color={colors.grey600}>
              신고자: 신고자C
            </Text>
            <Text typography='t7' color={colors.grey500}>
              2024-12-21
            </Text>
          </S.InfoRow>

          <S.ButtonGroup>
            <Button
              size='medium'
              style={
                {
                  flex: 1,
                  '--button-background-color': colors.green400,
                } as CSSProperties
              }
            >
              <Text typography='t6' color={colors.whiteOpacity900}>
                복구
              </Text>
            </Button>
            <Button
              size='medium'
              style={
                {
                  width: '80px',
                  '--button-background-color': colors.red500,
                } as CSSProperties
              }
            >
              <Text typography='t6' color={colors.whiteOpacity900}>
                삭제
              </Text>
            </Button>
          </S.ButtonGroup>
        </S.ReportCard>
      </S.ListContainer>

      {/* 하단 안내 문구 */}
      <S.NoticeBar>
        <Paragraph typography='t5'>
          <Paragraph.Icon name='icon-bulb' />

          <Paragraph.Text
            typography='t6'
            color={colors.blue700}
            fontWeight='medium'
            style={{ paddingLeft: '8px' }}
          >
            숨김 처리 시 사용자에게 즉시 비노출됩니다.
          </Paragraph.Text>
        </Paragraph>
      </S.NoticeBar>
    </S.Container>
  );
};

export default AdminDashBoardPage;
