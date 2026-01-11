// components/DashboardStats.tsx
import * as S from '@/components/layout/DashBoardStats/style'
import { Text } from '@toss/tds-mobile';
import { colors } from '@toss/tds-colors';

interface StatData {
  label: string;
  count: number;
  color: string;
  bgColor: string;
}

interface Props {
  stats: StatData[];
}

const DashboardStats = ({ stats }: Props) => {
  return (
    <S.StatsGrid>
      {stats.map((stat, index) => (
        <S.StatCard key={index} bgColor={stat.bgColor}>
          <Text typography="t6" color={colors.grey700}>
            {stat.label}
          </Text>
          <Text typography="t3" fontWeight="bold" color={stat.color}>
            {stat.count}
          </Text>
        </S.StatCard>
      ))}
    </S.StatsGrid>
  );
};

export default DashboardStats;