import * as S from '@/components/layout/ReportCard/style';
import { colors } from '@toss/tds-colors';
import { Badge, Button, ListRow, Text } from '@toss/tds-mobile';
import { type CSSProperties } from 'react';

export interface ReportData {
  id: number;
  truckName: string;
  reportType: string;
  status: 'exposed' | 'hidden';
  content: string;
  reason: string;
  reporter: string;
  date: string;
}

interface Props {
  data: ReportData;
}

const ReportCard = ({ data }: Props) => {
  const isExposed = data.status === 'exposed';

  return (
    <S.ReportCard isHighlighted={isExposed}>
      <ListRow
        left={
          <ListRow.AssetIcon
            name={
              data.reportType === '리뷰 신고'
                ? 'icon-chat-bubble-grayline-mono'
                : 'icon-pin-location-mono'
            }
          />
        }
        contents={
          <ListRow.Texts
            type="2RowTypeA"
            top={data.truckName}
            bottom={data.reportType}
          />
        }
        right={
          <Badge
            size="small"
            color={isExposed ? 'red' : 'elephant'}
            variant="weak"
          >
            {isExposed ? '노출 중' : '숨김'}
          </Badge>
        }
        style={{ padding: 0 }}
      />

      <S.ContentBox>
        <Text typography="t5" fontWeight="medium">
          {data.content}
        </Text>
        <Text typography="t6" color={colors.grey600}>
          신고 사유: {data.reason}
        </Text>
      </S.ContentBox>

      <S.InfoRow>
        <Text typography="t6" color={colors.grey600}>
          신고자: {data.reporter}
        </Text>
        <Text typography="t7" color={colors.grey500}>
          {data.date}
        </Text>
      </S.InfoRow>

      <S.ButtonGroup>
        <Button
          size="medium"
          style={
            {
              flex: 1,
              '--button-background-color': isExposed
                ? colors.orange400
                : colors.green400,
            } as CSSProperties
          }
        >
          <Text typography="t6" color={colors.whiteOpacity900}>
            {isExposed ? '숨김' : '복구'}
          </Text>
        </Button>
        <Button
          size="medium"
          style={
            {
              width: '80px',
              '--button-background-color': colors.red500,
            } as CSSProperties
          }
        >
          <Text typography="t6" color={colors.whiteOpacity900}>
            삭제
          </Text>
        </Button>
      </S.ButtonGroup>
    </S.ReportCard>
  );
};

export default ReportCard;